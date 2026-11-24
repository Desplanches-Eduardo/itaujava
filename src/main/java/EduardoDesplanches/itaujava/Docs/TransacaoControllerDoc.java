package EduardoDesplanches.itaujava.Docs;

import EduardoDesplanches.itaujava.transacao.TransacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Transações", description = "Gerenciamento de transações financeiras")
public interface TransacaoControllerDoc {
    @Operation(
            summary = "Registrar uma nova transação",
            description = """
            Registra uma nova transação financeira (armazenada em memória).

            Regras aplicadas:
            - O valor deve ser maior que zero;
            - A data/hora não pode estar no futuro;
            - Ambos os campos são obrigatórios.

            Se alguma regra for violada, a API responde 422 sem corpo.
            """
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Transação registrada com sucesso (dataHora devolvida em UTC)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransacaoDTO.class),
                            examples = @ExampleObject(
                                    name = "TransacaoCriada",
                                    value = """
                        {
                          "valor": 150.75,
                          "dataHora": "2024-01-15T13:30:00Z"
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "JSON malformado ou ilegível (corpo da resposta vazio)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = """
                            Transação recusada pelas regras de negócio: valor ausente,
                            menor ou igual a zero, data/hora ausente ou no futuro
                            (corpo da resposta vazio)
                            """,
                    content = @Content
            )
    })
    ResponseEntity<TransacaoDTO> adicionar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da transação a ser registrada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransacaoDTO.class),
                            examples = @ExampleObject(
                                    name = "NovaTransacao",
                                    value = """
                    {
                      "valor": 150.75,
                      "dataHora": "2024-01-15T10:30:00-03:00"
                    }
                    """
                            )
                    )
            )
            @Valid @RequestBody TransacaoDTO dto
    );

    @Operation(
            summary = "Deletar todas as transações",
            description = """
            Remove TODAS as transações armazenadas em memória.
            Operação irreversível.
            """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Todas as transações foram removidas com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno inesperado (corpo da resposta vazio)",
                    content = @Content
            )
    })
    ResponseEntity<Void> deletar();
}
