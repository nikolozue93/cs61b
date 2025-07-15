package ngrams;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.In;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;
import static utils.Utils.SHORT_WORDS_FILE;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    private String wordsFilename;
    private String countsFilename;
    Map wordCounts;
    TimeSeries totalCounts;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordCounts = new HashMap<String, TimeSeries>();
        totalCounts = new TimeSeries();

        In words = new In(wordsFilename);
        In counts = new In(countsFilename);

        // for wordCounts
        while(!words.isEmpty()){
            String line = words.readLine(); // reading line
            String[] splitLine = line.split("\t"); // splitting line on tab characters

            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double count = Double.parseDouble(splitLine[2]);

            if(!wordCounts.containsKey(word)){
                wordCounts.put(word, new TimeSeries());
            }

            TimeSeries tempTS = (TimeSeries) wordCounts.get(word);
            tempTS.put(year, count);
        }

        // for total counts
        while(!counts.isEmpty()){
            String line = counts.readLine();
            String[] splitLine = line.split(","); // splitting line at comma characters

            int year = Integer.parseInt(splitLine[0]);
            Double totalNum = Double.parseDouble(splitLine[1]);

            totalCounts.put(year, totalNum);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries history = countHistory(word);

        if(!history.isEmpty()){
            TimeSeries fullHistory = new TimeSeries(history, startYear, endYear);

            return fullHistory;
        }

        return history;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if(wordCounts.containsKey(word)){
            TimeSeries defensiveCopy = (TimeSeries) wordCounts.get(word);
            return defensiveCopy;
        }
        return new TimeSeries();
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries defensiveCopy = totalCounts;
        return defensiveCopy;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries weights = weightHistory(word);

        TimeSeries weightsHistory = new TimeSeries(weights, startYear, endYear);
        return weightsHistory;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries countHistory = countHistory(word);
        TimeSeries totalHistory = totalCountHistory();

        TimeSeries weightHistory = countHistory.dividedBy(totalHistory);
        return weightHistory;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries totalWeights = new TimeSeries();

        for(String word : words){ // as .plus() returns new timeseries, we have re assign with each iteration
            totalWeights = totalWeights.plus(weightHistory(word, startYear, endYear));
        }

        return totalWeights;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries totalWeights = new TimeSeries();

        for(String word : words){
            totalWeights.plus(weightHistory(word));
        }
        return totalWeights;
    }
}
