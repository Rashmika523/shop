package com.dev.pos.dao.custom;

import com.dev.pos.Entity.Batch;
import com.dev.pos.dao.CrudDao;
import com.dev.pos.dto.ProductDetailsJoinDto;

import java.sql.SQLException;
import java.util.List;

public interface BatchDao extends CrudDao<Batch,String> {

    public List<Batch> findAllBatch(int productCode) throws SQLException, ClassNotFoundException;

    public ProductDetailsJoinDto findProductDetailJoinData(String code) throws SQLException, ClassNotFoundException;

    public boolean manageQty(String barcode,int qty) throws SQLException, ClassNotFoundException;
}
