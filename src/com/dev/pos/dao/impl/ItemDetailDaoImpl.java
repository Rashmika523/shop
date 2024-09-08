package com.dev.pos.dao.impl;

import com.dev.pos.Entity.ItemDetail;
import com.dev.pos.dao.CrudUtil;
import com.dev.pos.dao.custom.ItemDetailDao;

import java.sql.SQLException;
import java.util.List;

public class ItemDetailDaoImpl implements ItemDetailDao {
    @Override
    public boolean save(ItemDetail itemDetail) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO order_detail_has_prodct_details VALUES (?,?,?,?,?)";
        return  CrudUtil.execute(sql,
                itemDetail.getOrderDetailCode(),
                itemDetail.getOrder(),
                itemDetail.getQty(),
                itemDetail.getDiscount(),
                itemDetail.getAmount()
        );
    }

    @Override
    public boolean update(ItemDetail itemDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ItemDetail find(Integer integer) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ItemDetail> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ItemDetail> search(Integer integer) throws SQLException, ClassNotFoundException {
        return null;
    }
}
