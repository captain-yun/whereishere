package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Comment;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findCommentListByCompany(Company company) {

        List<Comment> comments = company.getComments();

        return comments;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
