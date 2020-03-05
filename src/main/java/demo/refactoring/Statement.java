package demo.refactoring;

import java.util.Map;

public class Statement {

    public static String print(Invoice invoice, Map<String, Play> plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder("Statement for " + invoice.getCustomer() + "\n");

        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            int thisAmount;

            thisAmount = amountFor(perf, play);

            // ボリューム特典ポイントを加算
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // 喜劇のときは10人につき、さらにポイントを加算
            if ("comendy".equals(play.getType())) volumeCredits += Math.floor(perf.getAudience() / 5.0);
            // 注文の内訳を出力
            result.append("  ").append(play.getName()).append(": ").append(thisAmount / 100).append(" (").append(perf.getAudience()).append(" seats)\n");
            totalAmount += thisAmount;
        }
        result.append("Amount owed is ").append(totalAmount / 100).append("\n");
        result.append("You earned ").append(volumeCredits).append(" credits");
        return result.toString();
    }

    private static int amountFor(Performance perf, Play play) throws Exception {
        int result;
        switch (play.getType()) {
            case "tragedy":
                result = 40000;
                if (perf.getAudience() > 30) {
                    result += 1000 * (perf.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.getAudience() > 30) {
                    result += 10000 + 500 * (perf.getAudience() - 20);
                }
                result += 300 * perf.getAudience();
                break;
            default:
                throw new Exception("unknown type " + play.getType());
        }
        return result;
    }

}
