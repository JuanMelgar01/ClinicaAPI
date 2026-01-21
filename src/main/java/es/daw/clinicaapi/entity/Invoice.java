package es.daw.clinicaapi.entity;

import es.daw.clinicaapi.enums.InvoiceStatus;
import es.daw.clinicaapi.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="invoices")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="appointment_id", nullable = false, unique=true)
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private InvoiceStatus status;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal subtotal;

    @Column(name = "tax_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal taxTotal;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal total;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;
    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", length = 20)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceLine> lines = new ArrayList<>();

}

