package demo.refactoring;

import java.util.Map;

public class Statement {

    public static String print(Invoice invoice, Map<String, Play> plays) throws Exception {
        StringBuilder result = new StringBuilder("Statement for " + invoice.getCustomer() + "\n");

        int totalAmount = 0;
        for (Performance perf : invoice.getPerformances()) {
            // 注文の内訳を出力
            result.append("  ").append(playFor(plays, perf).getName()).append(": ").append(amountFor(perf, playFor(plays, perf)) / 100).append(" (").append(perf.getAudience()).append(" seats)\n");
            totalAmount += amountFor(perf, playFor(plays, perf));
        }

        result.append("Amount owed is ").append(totalAmount / 100).append("\n");
        result.append("You earned ").append(totalVolumeCredits(invoice, plays)).append(" credits");
        return result.toString();
    }

    private static int totalVolumeCredits(Invoice invoice, Map<String, Play> plays) {
        int volumeCredits = 0;
        for (Performance perf : invoice.getPerformances()) {
            volumeCredits += volumeCreditsFor(perf, plays);
        }
        return volumeCredits;
    }

    private static int volumeCreditsFor(Performance perf, Map<String, Play> plays) {
        int volumeCredits = 0;
        // ボリューム特典ポイントを加算
        volumeCredits += Math.max(perf.getAudience() - 30, 0);
        // 喜劇のときは10人につき、さらにポイントを加算
        if ("comendy".equals(playFor(plays, perf).getType())) volumeCredits += Math.floor(perf.getAudience() / 5.0);
        return volumeCredits;
    }

    private static Play playFor(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.getPlayID());
    }

    private static int amountFor(Performance aPerformance, Play play) throws Exception {
        int result;
        switch (play.getType()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.getAudience() > 30) {
                    result += 10000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
                break;
            default:
                throw new Exception("unknown type " + play.getType());
        }
        return result;
    }

}
