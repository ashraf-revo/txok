package org.revo.txok.Service.Impl;

import org.revo.txok.Domain.Exam;
import org.revo.txok.Repository.ExamRepository;
import org.revo.txok.Service.ExamService;
import org.revo.txok.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserService userService;

    @Override
    public Mono<Exam> save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Mono<Void> delete(Exam exam) {
        return examRepository.findById(exam.getId())
                .filter(it -> it.getUserId() != null)
                .filter(it -> it.getUserId().equals(userService.currentUser().orElse(null)))
                .flatMap(it -> examRepository.delete(exam));
    }
}
