package com.dev.pos.bo.custom;

import com.dev.pos.dto.OrderDetailDTO;

public interface OrderDetailBo {

    public boolean makeOrder(OrderDetailDTO dto);
}
