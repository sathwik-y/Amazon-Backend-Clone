package cloneamazon.Amazon.Clone.Entity;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String paymentMethod;
    private Double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public enum paymentStatus{
        PENDING,
        PROCESSING,
        AUTHORIZED,
        COMPLETED,
        FAILED,
        DECLINED,
        REFUND_PENDING,
        REFUNDED,
        CANCELLED,
        ERROR
    }
}