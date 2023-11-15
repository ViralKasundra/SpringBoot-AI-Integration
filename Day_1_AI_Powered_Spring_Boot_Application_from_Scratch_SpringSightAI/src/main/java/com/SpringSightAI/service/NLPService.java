package com.SpringSightAI.SpringSightAI.service;

import com.SpringSightAI.SpringSightAI.model.Text;
import com.SpringSightAI.SpringSightAI.repository.TextRepository;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class NLPService {

    private final TextRepository textRepository;
    private final StanfordCoreNLP pipeline;

    @Autowired
    public NLPService(TextRepository textRepository) {
        this.textRepository = textRepository;

        // Initialize the Stanford CoreNLP pipeline
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        this.pipeline = new StanfordCoreNLP(properties);
    }

    /**
     * Save text to the database.
     *
     * @param inputText Input text to be saved.
     * @return Saved Text entity.
     */
    public Text saveText(String inputText) {
        Text savedText = textRepository.save(new Text(inputText));
        return savedText;
    }

    /**
     * Analyze the sentiment of a given text.
     *
     * @param inputText Input text for sentiment analysis.
     * @return Sentiment analysis result as a String.
     */
    public String analyzeSentiment(String inputText) {
        // Create a CoreDocument from the input text
        CoreDocument document = new CoreDocument(inputText);

        // Annotate the document to perform sentiment analysis
        pipeline.annotate(document);

        // Get the sentiment of the document
        String sentiment = extractSentiment(document);

        return "Sentiment: " + sentiment;
    }

    /**
     * Retrieve all texts from the database.
     *
     * @return List of Text entities.
     */
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    /**
     * Extract sentiment from the annotated CoreDocument.
     *
     * @param document Annotated CoreDocument.
     * @return Sentiment as a String.
     */
    private String extractSentiment(CoreDocument document) {
        CoreMap sentence = document.sentences().get(0); // Assuming a single sentence for simplicity
        return sentence.get(SentimentCoreAnnotations.SentimentClass.class);
    }
}
