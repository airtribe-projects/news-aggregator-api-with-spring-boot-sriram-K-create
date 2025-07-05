// TODO: Implement NewsService.java
// TODO: Implement NewsService.java
@Service
public class NewsService {
    private final WebClient client = WebClient.create("https://gnews.io/api/v4");
    private final String apiKey = "YOUR_API_KEY"; // replace with your real key

    @Cacheable("news")
    public Mono<String> fetchNews(String keyword) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("q", keyword)
                        .queryParam("token", apiKey).build())
                .retrieve().bodyToMono(String.class);
    }

    @Scheduled(fixedRate = 600000) // every 10 min
    @CacheEvict(value = "news", allEntries = true)
    public void evictCache() {
        System.out.println("Evicted news cache");
    }
}
