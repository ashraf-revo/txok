package org.revo.txok.Service;

import org.revo.txok.Domain.Exam;
import org.revo.txok.Domain.Question;
import reactor.core.publisher.Mono;

public interface QuestionService {
    Mono<Question> save(Question question, Exam exam);

    Mono<Void> delete(Question question);

}
