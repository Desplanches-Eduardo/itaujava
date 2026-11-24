package EduardoDesplanches.itaujava.Docs;

import EduardoDesplanches.itaujava.Estatistica.EstatisticaModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Estatísticas", description = "Cálculo de estatísticas das transações recentes")
    public interface EstatisticaControllerDoc {

        @Operation(
                summary = "Obter estatísticas das transações recentes",
                description = """
            Calcula estatísticas (count, avg, max, min, sum) das transações
            registradas dentro da janela de tempo configurada em
            `estatistica.segundos` (padrão: últimos 60 segundos).

            Caso não existam transações na janela, todos os campos são retornados
            com valor `0`.
            """
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "Estatísticas calculadas com sucesso",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = EstatisticaModel.class),
                                examples = {
                                        @ExampleObject(
                                                name = "ComTransacoes",
                                                summary = "Janela com transações",
                                                value = """
                            {
                              "count": 3,
                              "avg": 200.50,
                              "max": 300.00,
                              "min": 100.50,
                              "sum": 601.50
                            }
                            """
                                        ),
                                        @ExampleObject(
                                                name = "SemTransacoes",
                                                summary = "Janela sem transações",
                                                value = """
                            {
                              "count": 0,
                              "avg": 0.0,
                              "max": 0.0,
                              "min": 0.0,
                              "sum": 0.0
                            }
                            """
                                        )
                                }
                        )
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Erro interno inesperado (corpo da resposta vazio)",
                        content = @Content
                )
        })
        ResponseEntity<EstatisticaModel> estatistica();
    }
