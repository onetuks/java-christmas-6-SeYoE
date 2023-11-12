package christmas.io;

import camp.nextstep.edu.missionutils.Console;
import christmas.order.OrderMenus;
import christmas.order.VisitDate;

public class InputView {

    private static final String VISIT_DATE_QUERY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_COUNT_QUERY = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public VisitDate inputVisitDate() {
        while (true) {
            System.out.println(VISIT_DATE_QUERY);

            try {
                String inputDate = Console.readLine();
                return new VisitDate(inputDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public OrderMenus inputMenuOrder() {
        while (true) {
            System.out.println(MENU_COUNT_QUERY);

            try {
                String inputMenuLine = Console.readLine();

                return new OrderMenus(inputMenuLine);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
