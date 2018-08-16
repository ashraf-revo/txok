package org.revo.txok.Service.Impl;

import org.revo.txok.Domain.Exam;
import org.revo.txok.Domain.Question;
import org.revo.txok.Repository.ExamRepository;
import org.revo.txok.Repository.QuestionRepository;
import org.revo.txok.Service.QuestionService;
import org.revo.txok.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserService userService;

    @Override
    public Mono<Question> save(Question question, Exam exam) {
        return examRepository.findById(exam.getId())
                .filter(it -> it.getUserId() != null)
                .filter(it -> it.getUserId().equals(userService.currentUser().orElse(null)))
                .map(it -> {
                    question.setExamId(it.getId());
                    return question;
                })
                .flatMap(it -> questionRepository.save(question));
    }

    @Override
    public Mono<Void> delete(Question question) {
        return questionRepository.findById(question.getId())
                .flatMap(it -> examRepository.findById(it.getExamId()))
                .filter(it -> it.getUserId() != null)
                .filter(it -> it.getUserId().equals(userService.currentUser().orElse(null)))
                .flatMap(it -> questionRepository.deleteById(question.getId()));
    }
}
