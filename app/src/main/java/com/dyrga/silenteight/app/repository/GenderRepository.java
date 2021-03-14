package com.dyrga.silenteight.app.repository;

import com.dyrga.silenteight.alg.model.GenderAlgRequest;
import com.dyrga.silenteight.app.model.db.NameGender;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends PagingAndSortingRepository<NameGender, Long> {
    @Query("SELECT n FROM NameGender n WHERE n.name in :names")
    List<GenderAlgRequest> getMatchingTokens(String[] names);
}
