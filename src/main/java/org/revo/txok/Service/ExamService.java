package org.revo.txok.Service;

import org.revo.txok.Domain.Exam;
import reactor.core.publisher.Mono;

public interface ExamService {
    Mono<Exam> save(Exam exam);

    Mono<Void> delete(Exam exam);

}
