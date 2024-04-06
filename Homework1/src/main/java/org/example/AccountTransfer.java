package org.example;


public class AccountTransfer {
    // 이제 transfer 메서드는 상대방 계좌번호 검증 후 이체를 진행
    public void transfer(Account fromAccount, String toAccountNumber, double amount) {
        // 여기서는 예제로 "987-654-321"이 유효한 계좌번호로 가정
        if ("987-654-321".equals(toAccountNumber)) {
            if (fromAccount.withdraw(amount)) {
                // 실제 애플리케이션에서는 여기서 toAccount의 deposit 메서드를 호출
                System.out.println("이체가 완료되었습니다. 이체 금액: " + amount + "원");
            } else {
                System.out.println("잔액이 부족하여 이체를 진행할 수 없습니다.");
            }
        } else {
            System.out.println("유효하지 않은 계좌번호입니다. 이체를 중단합니다.");
        }
    }
}

