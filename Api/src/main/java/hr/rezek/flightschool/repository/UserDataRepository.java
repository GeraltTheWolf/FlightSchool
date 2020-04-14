package hr.rezek.flightschool.repository;

import hr.rezek.flightschool.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
