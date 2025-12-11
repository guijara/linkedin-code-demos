# 💉 01 - Field Injection vs Constructor Injection

Este projeto demonstra a diferença prática e arquitetural entre usar @Autowired diretamente nos atributos (Field Injection) e utilizar a Injeção via Construtor em aplicações Spring Boot.

O objetivo é evidenciar por que a Injeção via Construtor é a abordagem recomendada para criar softwares mais robustos, testáveis e manuteníveis.

## 🚫 O Problema: Field Injection

Muitos tutoriais ensinam a injetar dependências assim:

    @Service
    public class BadService {
    @Autowired
    private Repository repository; // ⚠️ Oculto e Mutável
    }

Embora pareça limpo, essa abordagem traz problemas sérios:

Dependências Ocultas: Ao olhar para a classe de fora, não sabemos o que ela precisa para funcionar. Isso facilita a criação de classes que fazem coisas demais (violação do Single Responsibility Principle).

Impossibilidade de Imutabilidade: Não podemos usar a palavra-chave final, o que significa que as dependências podem ser alteradas em tempo de execução.

Acoplamento com o Framework: A classe se torna difícil de instanciar sem o Spring Container.

## ✅ A Solução: Constructor Injection

A abordagem recomendada inverte o controle de forma explícita:

    @Service
    public class GoodService{
        
        private final Repository repository; // 🔒 Imutável

    //  O Spring injeta automaticamente (mesmo sem @Autowired nas versões novas)

        public GoodService(Repository repository) {
        this.repository = repository;
        }

    }
## 🧠 Principais Vantagens
1. Testabilidade
   Com Field Injection, você é obrigado a usar Reflection ou subir o Contexto do Spring (@SpringBootTest) apenas para testar uma lógica simples, o que torna os testes lentos. Com Constructor Injection, o teste unitário é puro Java. Você não precisa do Spring:


    // Teste Unitário simples e rápido
    @Test
    void deveSalvarPedido() {
        // Mockar é trivial, basta passar no construtor
        Repository mockRepo = Mockito.mock(Repository.class);
        GoodService service = new GoodService(mockRepo);

        service.processar();
    
        verify(mockRepo).save(any());
    }
2. Imutabilidade e Segurança (final)
   Ao usar o construtor, podemos marcar os atributos como final. Isso garante que, uma vez que o componente (Bean) é criado, suas dependências nunca serão alteradas ou tornar-se-ão null acidentalmente. Isso previne uma série de bugs de concorrência e estado inválido.

3. Evitando "God Classes"
   Se você precisa adicionar 10 argumentos no construtor, ficará visualmente óbvio que sua classe está "gorda" demais e precisa ser refatorada. Com Field Injection, é fácil adicionar 10 @Autowired sem perceber a complexidade crescendo.

🛠️ Tecnologias Utilizadas
Java 17+

Spring Boot 3.x

JUnit 5 & Mockito (para demonstração de testes)

🔗 Meu perfil para conectar-se

[LinkedIn](https://www.linkedin.com/in/guilhermejara/)
