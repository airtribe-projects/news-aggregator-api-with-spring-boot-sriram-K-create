// TODO: Implement UserRepository.java
// TODO: Implement UserRepository.java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
