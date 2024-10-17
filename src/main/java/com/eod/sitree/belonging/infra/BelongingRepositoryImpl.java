package com.eod.sitree.belonging.infra;

import com.eod.sitree.belonging.domain.model.Belonging;
import com.eod.sitree.belonging.domain.modelRepository.BelongingRepository;
import com.eod.sitree.belonging.infra.entity.BelongingEntity;
import com.eod.sitree.belonging.infra.entity.QBelongingEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BelongingRepositoryImpl implements BelongingRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final BelongingJpaRepository belongingJpaRepository;

    private final QBelongingEntity belongingEntity = QBelongingEntity.belongingEntity;

    @Override
    public List<Belonging> searchByName(String name) {

        return jpaQueryFactory.selectFrom(belongingEntity)
            .where(belongingEntity.name.like("%" + name + "%"))
            .fetch().stream()
            .map(BelongingEntity::toDomainModel).toList();
    }
}
