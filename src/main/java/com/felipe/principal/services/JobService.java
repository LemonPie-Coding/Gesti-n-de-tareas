package com.felipe.principal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.principal.models.Job;
import com.felipe.principal.repositories.JobRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JobService extends BaseService {


    @Autowired
    private final JobRepository jobRepository;

    @Override
    public Object findAll() {
        // TODO Auto-generated method stub
        return jobRepository.findAll();
    }

    @Override
    public Object findById(Long id) {
        // TODO Auto-generated method stub
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Object create(Object object) {
        // TODO Auto-generated method stub
        return jobRepository.save((Job) object);
    }

    public Job update(Object object) {
        return jobRepository.save((Job) object);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        jobRepository.deleteById(id);
    }

    public List<Job> findAllByOrderByPriorityAsc() {
        return jobRepository.findAllByOrderByPriorityAsc();
    }
    
    public List<Job> findAllByOrderByPriorityDesc() {
        return jobRepository.findAllByOrderByPriorityDesc();
    }
    
    public int countJobsByUserId(Long userId) {
        return jobRepository.countByUsers_Id(userId);
    }
    
}
