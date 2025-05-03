package co.edu.uniquindio.clinica.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
@Getter
@Builder
public class Factura {
    private final String id;
    private final LocalDateTime fecha;
    private final double subtotal;
    private final double total;

    private Factura(FacturaBuilder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.subtotal = builder.subtotal;
        this.total = builder.total;
    }

    public static class FacturaBuilder {
        private String id;
        private LocalDateTime fecha;
        private double subtotal;
        private double total;

        public FacturaBuilder id(String id) {
            this.id = id;
            return this;
        }

        public FacturaBuilder fecha(LocalDateTime fecha) {
            this.fecha = fecha;
            return this;
        }

        public FacturaBuilder subtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public FacturaBuilder total(double total) {
            this.total = total;
            return this;
        }

        public Factura build() {
            return new Factura(this);
        }
    }
}
