package be.timmcca.entitymapping;

import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import be.timmcca.entitymapping.application.BankingService;
import be.timmcca.entitymapping.infrastructure.UnitOfWork;

public class App  {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private static void perform(Consumer<BankingService> consumer) {
        try (var session = sessionFactory.openSession()) {
            var service = new BankingService(new UnitOfWork(session));
            consumer.accept(service);
        }
    }

    private static <T> T performAndReturn(Function<BankingService, T> function) {
        try (var session = sessionFactory.openSession()) {
            var service = new BankingService(new UnitOfWork(session));
            return function.apply(service);
        }
    }
    
    public static void main(String[] args) {
        // each task is performed with a new DB session to simulate requests in an API project
        perform(service -> service.openAccount("Tim"));
        perform(service -> service.depositToChecking(1, 100));
        perform(service -> service.depositToSavings(1, 50));
        perform(service -> service.transferToSavings(1, 75));
        var user = performAndReturn(service -> service.getUser(1));
        System.out.println("User name (should be Tim): " + user.getName());
        System.out.println("Checking balance (should be 25): " + user.getCheckingAccount().getBalance());
        System.out.println("Savings balance (should be 125): " + user.getSavingsAccount().getBalance());
    }
}
