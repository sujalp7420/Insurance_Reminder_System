package com.InsRem.repository;
import com.InsRem.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<
                AuditLog,
                Long> {
}