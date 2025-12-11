package com.exemplo.injecao.service;

import com.exemplo.injecao.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadService {

    // ❌ ERRO 1: Dependência oculta. Quem instancia a classe não sabe que isso é obrigatório.
    // ❌ ERRO 2: Não é final. Pode ser alterado via Reflection ou métodos internos.
    @Autowired
    private PedidoRepository repository;

    public void processarPedido(String pedido) {
        // Se instanciarmos BadService com "new BadService()", isso dá NullPointerException aqui.
        repository.salvar(pedido);
        System.out.println("Pedido processado via BadService.");
    }
}