package seoul.dietdiary.repository;

public interface CustomItemRepository {
    void updateItemQuantity(String name, float newQuantity);
}
