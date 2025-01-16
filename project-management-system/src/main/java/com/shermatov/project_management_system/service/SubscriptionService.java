package com.shermatov.project_management_system.service;

import com.shermatov.project_management_system.model.PlanType;
import com.shermatov.project_management_system.model.Subscription;
import com.shermatov.project_management_system.model.User;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getSubscription(Long userId);
    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);

}
