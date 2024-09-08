package com.dev.pos.dao.impl;

import com.dev.pos.Entity.OrderDetail;
import com.dev.pos.dao.CrudUtil;
import com.dev.pos.dao.custom.OrderDetailDao;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao{


    @Override
    public boolean save(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO order_detail VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,
                orderDetail.getCode(),
                orderDetail.getIssueDate(),
                orderDetail.getTotalCost(),
                orderDetail.getCustomerEmail(),
                orderDetail.getDiscount(),
                orderDetail.getUserEmail()
        );
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail find(Integer integer) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderDetail> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderDetail> search(Integer integer) throws SQLException, ClassNotFoundException {
        return null;
    }


    /*Transaction
    Set autoCommit =false;
    save Order;
    item List=>one by one save;
    update product qty;
    return true;
    commit;
    set autoCommit = true;*/
}
