package field.repositories;

import field.models.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {
}
