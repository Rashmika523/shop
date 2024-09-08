package com.dev.pos.bo.impl;

import com.dev.pos.Entity.ItemDetail;
import com.dev.pos.Entity.OrderDetail;
import com.dev.pos.Enum.DaoType;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.BatchDao;
import com.dev.pos.dao.custom.ItemDetailDao;
import com.dev.pos.dao.custom.OrderDetailDao;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.ItemDetailDTO;
import com.dev.pos.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailBo implements com.dev.pos.bo.custom.OrderDetailBo {

    OrderDetailDao detailDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);
    ItemDetailDao itemDetailDao = DaoFactory.getInstance().getDao(DaoType.ITEM_DETAIL);
    BatchDao batchDao = DaoFactory.getInstance().getDao(DaoType.BATCH);

    @Override
    public boolean makeOrder(OrderDetailDTO dto) {

        Connection connection = null;
        try {

            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            OrderDetail orderDetail = new OrderDetail(
                    dto.getCode(),
                    dto.getIssueDate(),
                    dto.getTotalCost(),
                    dto.getCustomerEmail(),
                    dto.getDiscount(),
                    dto.getUserEmail()
            );

            boolean isOrderSave = detailDao.save(orderDetail);

            if (isOrderSave) {

                boolean isItemDetailSave = saveItemDetails(dto.getDtoList(), dto.getCode());

                if (isItemDetailSave){
                    connection.commit();
                    return true;
                }else {
                    connection.rollback();
                    return false;
                }

            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean saveItemDetails(List<ItemDetailDTO> list, int orderCode) throws SQLException, ClassNotFoundException {
        for (ItemDetailDTO dto : list) {
            boolean isItemSave = itemDetailDao.save(new ItemDetail(
                    orderCode,
                    dto.getDetailCode(),
                    dto.getQty(),
                    dto.getDiscount(),
                    dto.getAmount()
            ));

            if (isItemSave) {
                if (!updateQty(dto.getDetailCode(), dto.getQty())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }


    private boolean updateQty(String barcode, int qty) throws SQLException, ClassNotFoundException {
        return batchDao.manageQty(barcode, qty);
    }
}
