import org.apache.lucene.search.similarities.TFIDFSimilarity;

public class MySolrSimilarity extends TFIDFSimilarity {

    @Override
    public float tf(float v) {
        return 1.0f;
    }

    @Override
    public float idf(long l, long l1) {
        return 1.0f;
    }

    @Override
    public float lengthNorm(int i) {
        return 1.0f;
    }
}
