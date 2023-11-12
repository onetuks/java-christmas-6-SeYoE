package christmas.vo;

import static christmas.error.ErrorMessage.INVALID_DATE;
import static christmas.error.ErrorMessage.NOT_DIGIT_DATE;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class VisitDate {

    private static final List<Integer> STAR_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;

    private final int date;
    private final boolean isStarDay;

    public VisitDate(final String date) {
        int parsedDate = parseDate(date);

        validateDate(parsedDate);

        this.date = parsedDate;
        this.isStarDay = getIsStarDay(parsedDate);
    }

    public int getDate() {
        return date;
    }

    public boolean isStarDay() {
        return isStarDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VisitDate visitDate = (VisitDate) o;
        return date == visitDate.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VisitDate.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .toString();
    }

    private int parseDate(final String date) {
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_DIGIT_DATE.getMessage());
        }
    }

    private void validateDate(final int date) {
        if (isLessThanFirstDate(date) || isGreaterThanLastDate(date)) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    private boolean isGreaterThanLastDate(final int date) {
        return date > LAST_DATE;
    }

    private boolean isLessThanFirstDate(final int date) {
        return date < FIRST_DATE;
    }

    private boolean getIsStarDay(final int date) {
        return STAR_DAYS.contains(date);
    }

}
