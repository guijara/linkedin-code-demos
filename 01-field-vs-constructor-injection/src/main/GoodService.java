package com.exemplo.injecao.service;

import com.exemplo.injecao.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    // ✅ VANTAGEM 1: Imutabilidade. O repository nunca será null ou trocado após a criação.
    private final PedidoRepository repository;

    // ✅ VANTAGEM 2: Contrato explícito. Quem der "new GoodService" É OBRIGADO a passar o repositório.
    // (O @Autowired é opcional aqui nas versões novas do Spring, mas pode deixar se quiser ser explícito)
    public GoodService(PedidoRepository repository) {
        this.repository = repository;
    }

    public void processarPedido(String pedido) {
        repository.salvar(pedido);
        System.out.println("Pedido processado via GoodService.");
    }
}