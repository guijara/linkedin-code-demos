package com.exemplo.injecao.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository {

    public void salvar(String pedido) {
        System.out.println("Salvando pedido no banco de dados: " + pedido);
    }
}