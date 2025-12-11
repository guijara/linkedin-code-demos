package com.exemplo.injecao.service;

import com.exemplo.injecao.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class GoodServiceTest {

    @Test
    @DisplayName("Deve processar pedido usando injeção via construtor sem precisar do Spring")
    void deveProcessarPedidoCorretamente() {
        // Cenario
        // Criamos um Mock (simulação) do repositório
        PedidoRepository repositoryMock = Mockito.mock(PedidoRepository.class);

        // ✅ Instanciação Pura: Não precisamos de @SpringBootTest, @InjectMocks, nada pesado.
        // É apenas Java. Passamos a dependência no construtor.
        GoodService service = new GoodService(repositoryMock);

        String pedido = "Notebook Gamer";

        // Ação
        service.processarPedido(pedido);

        // Verificação
        // Verifica se o método salvar foi chamado 1 vez com o argumento "Notebook Gamer"
        verify(repositoryMock, times(1)).salvar(pedido);
    }
}