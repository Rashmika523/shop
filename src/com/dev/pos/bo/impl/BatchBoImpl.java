package com.dev.pos.bo.impl;

import com.dev.pos.Entity.Batch;
import com.dev.pos.Enum.DaoType;
import com.dev.pos.bo.custom.BatchBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.BatchDao;
import com.dev.pos.dto.BatchDTO;
import com.dev.pos.dto.ProductDetailsJoinDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchBoImpl implements BatchBo {

    BatchDao batchDao = DaoFactory.getInstance().getDao(DaoType.BATCH);

    @Override
    public boolean saveBatch(BatchDTO dto) throws SQLException, ClassNotFoundException {
        return batchDao.save(
                new Batch(
                        dto.getCode(),
                        dto.getBarcode(),
                        dto.getQtyOnHand(),
                        dto.getSellingPrice(),
                        dto.isAvailable(),
                        dto.getShowPrice(),
                        dto.getBuyingPrice(),
                        dto.getProductCode()
                ));
    }

    @Override
    public List<BatchDTO> findAllBatch(int productCode) throws SQLException, ClassNotFoundException {

        List<Batch> allBatch = batchDao.findAllBatch(productCode);
        List<BatchDTO> dtoList = new ArrayList<>();

        if(allBatch != null){
            for (Batch b : allBatch) {
                dtoList.add(new BatchDTO(
                        b.getCode(),
                        b.getBarcode(),
                        b.getQtyOnHand(),
                        b.getSellingPrice(),
                        b.isAvailable(),
                        b.getShowPrice(),
                        b.getBuyingPrice(),
                        b.getProductCode()
                ));
            }
            return dtoList;
        }
        return null;
    }

    @Override
    public BatchDTO findBatch(String code) throws SQLException, ClassNotFoundException {

        Batch batch = batchDao.find(code);

        if(batch!=null){
            return new BatchDTO(
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

        return null;
    }

    @Override
    public ProductDetailsJoinDto findProductJoinDetail(String code) throws SQLException, ClassNotFoundException {
        return batchDao.findProductDetailJoinData(code);
    }

}
