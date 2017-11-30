package br.com.cauejannini;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cauejannini.domain.Categoria;
import br.com.cauejannini.domain.Cidade;
import br.com.cauejannini.domain.Cliente;
import br.com.cauejannini.domain.Endereco;
import br.com.cauejannini.domain.Estado;
import br.com.cauejannini.domain.Pagamento;
import br.com.cauejannini.domain.PagamentoComBoleto;
import br.com.cauejannini.domain.PagamentoComCartao;
import br.com.cauejannini.domain.Pedido;
import br.com.cauejannini.domain.Produto;
import br.com.cauejannini.domain.enums.EstadoPagamento;
import br.com.cauejannini.domain.enums.TipoCliente;
import br.com.cauejannini.repositories.CategoriaRepository;
import br.com.cauejannini.repositories.CidadeRepository;
import br.com.cauejannini.repositories.ClienteRepository;
import br.com.cauejannini.repositories.EnderecoRepository;
import br.com.cauejannini.repositories.EstadoRepository;
import br.com.cauejannini.repositories.PagamentoRepository;
import br.com.cauejannini.repositories.PedidoRepository;
import br.com.cauejannini.repositories.ProdutoRepository;

@SpringBootApplication
public class Projeto2Application implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Projeto2Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática"); 
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "40636185877", TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("11976373668", "1132532615"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "ap 12", "Jardim Olinda", "01223000", cliente, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sl 800", "Centro", "01331010", cliente, c2);
		
		cliente.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cliente));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 11:20"), cliente, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cliente.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
				
	}
}
