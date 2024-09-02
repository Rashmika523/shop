package com.dev.pos.dao.impl;

import com.dev.pos.Entity.Batch;
import com.dev.pos.dao.CrudUtil;
import com.dev.pos.dao.custom.BatchDao;
import com.dev.pos.dto.BatchDTO;
import com.dev.pos.dto.ProductDetailsJoinDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchDaoImpl implements BatchDao {
    @Override
    public boolean save(Batch batch) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO batch VALUES (?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,
                batch.getCode(),
                batch.getBarcode(),
                batch.getQtyOnHand(),
                batch.getSellingPrice(),
                batch.isAvailable(),
                batch.getShowPrice(),
                batch.getBuyingPrice(),
                batch.getProductCode()
        );
    }

    @Override
    public boolean update(Batch batch) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Batch find(String s) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM batch WHERE code =?";
        ResultSet set = CrudUtil.execute(sql, s);
        if (set.next()) {
            return new Batch(
                    set.getString(1),
                    set.getString(2),
                    set.getInt(3),
                    set.getDouble(4),
                    set.getBoolean(5),
                    set.getDouble(6),
                    set.getDouble(7),
                    set.getInt(8)
            );
        }
        return null;
    }

    @Override
    public List<Batch> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Batch> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Batch> findAllBatch(int productCode) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM batch WHERE product_code =?";
        ResultSet set = CrudUtil.execute(sql, productCode);
        List<Batch> list = new ArrayList<>();

        while (set.next()) {

            list.add(new Batch(
                    set.getString(1),
                    set.getString(2),
                    set.getInt(3),
                    set.getDouble(4),
                    set.getBoolean(5),
                    set.getDouble(6),
                    set.getDouble(7),
                    set.getInt(8)
            ));
        }
        return list;
    }

    @Override
    public ProductDetailsJoinDto findProductDetailJoinData(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM batch b join product p ON b.code = ? AND b.product_code = p.code";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if (resultSet.next()) {
            return new ProductDetailsJoinDto(
                    resultSet.getInt(9),
                    resultSet.getString(10),
                    new BatchDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4),
                            resultSet.getBoolean(5),
                            resultSet.getDouble(6),
                            resultSet.getDouble(7),
                            resultSet.getInt(8)
                    )
            );
        }
        return null;
    }
}
