package dad.dad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.dad.model.Post;

public interface DadRepository extends JpaRepository<Post, Long> {

}
