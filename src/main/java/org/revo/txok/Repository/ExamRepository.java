package org.revo.txok.Repository;

import org.revo.txok.Domain.Exam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExamRepository extends ReactiveMongoRepository<Exam, String> {
}
