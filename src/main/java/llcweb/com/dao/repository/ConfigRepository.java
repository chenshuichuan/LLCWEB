package llcweb.com.dao.repository;

import llcweb.com.domain.models.Config;
import llcweb.com.domain.models.Platform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Config,Integer> {

    Page<Config> findAll(Specification<Config> spec, Pageable pageable);

    Config findByConfigKey(String key);
}
