package br.com.zupacademy.edu
import br.com.zupacademy.edu.carros.Carro
import br.com.zupacademy.edu.carros.CarroRepository
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(
    rollback = false,
    transactionMode = TransactionMode.SINGLE_TRANSACTION,
    transactional = false
)
class CarrosGrpcTest {

    /**
     * Estratégias
     * louça: sujou, limpou -> @AfterEach
     * louça: limpou, usou -> @BeforeEach [x]
     * louça: usa louça descartavel -> rollbak=true
     * louça: uso a louça, joga fora, compro nova -> recriar o banco a cada teste == banco em memória
     */

    @Inject
    lateinit var repository: CarroRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @AfterEach
    fun cleanUp() {
        repository.deleteAll()
    }

    @Test
    fun `deve inserir um novo carro`() {
        //acao
        repository.save(Carro("Gol", "HPX-1234"))

        //validacao
        assertEquals(1, repository.count())
    }//commit

    @Test
    fun `deve encontrar carro por placa`() {
        //cenario
        repository.save(Carro("Gol", "OPX-1234"))

        //acao
        val encontrado = repository.existsByPlaca("OPX-1234")
        //validacao
        assertTrue(encontrado)
    }

}
