package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 사용자의 계좌 예시 (실제 애플리케이션에서는 사용자 인증 후 계좌 정보를 로드해야 함..)
        Account fromAccount = new Account("123-456-789", 10000);

        // 이체 작업을 관리하는 객체
        AccountTransfer accountTransfer = new AccountTransfer();

        while (true) {
            System.out.println("\n=== 예적금 계좌 시스템 ===");
            System.out.println("1: 입금");
            System.out.println("2: 출금");
            System.out.println("3: 계좌 이체");
            System.out.println("4: 종료");
            System.out.print("실행할 작업을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1: // 입금
                    System.out.print("입금할 금액을 입력하세요: ");
                    double depositAmount = scanner.nextDouble();
                    fromAccount.deposit(depositAmount);
                    break;
                case 2: // 출금
                    System.out.print("출금할 금액을 입력하세요: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (!fromAccount.withdraw(withdrawAmount)) {
                        System.out.println("출금할 수 없습니다. 잔액을 확인해 주세요.");
                    }
                    break;
                case 3: // 계좌 이체
                    System.out.print("이체할 상대방 계좌번호를 입력하세요: ");
                    String toAccountNumber = scanner.nextLine();
                    System.out.print("이체할 금액을 입력하세요: ");
                    double transferAmount = scanner.nextDouble();

                    // 상대방 계좌번호 검증 없이 직접 이체를 시뮬레이션
                    // 실제 애플리케이션에서는 입력받은 계좌번호에 해당하는 계좌 객체를 찾아 이체를 진행
                    accountTransfer.transfer(fromAccount, toAccountNumber, transferAmount);
                    break;
                case 4: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해 주세요.");
                    break;
            }
        }
    }
}
