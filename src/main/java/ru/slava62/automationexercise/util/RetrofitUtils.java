package ru.slava62.automationexercise.util;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.slava62.automationexercise.dto.*;
import ru.slava62.automationexercise.service.*;


// import ru.slava62.service.ProductService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

@UtilityClass
public class RetrofitUtils {
   HttpLoggingInterceptor logging =  new HttpLoggingInterceptor(new PrettyLogger());

    public Retrofit getRetrofit() throws MalformedURLException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofMinutes(1l))
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build(); 

        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }

    public ProductService getProductService() throws MalformedURLException{
            return getRetrofit().create(ProductService.class);
        }
    
    public BrandService getBrandService() throws MalformedURLException{
            return getRetrofit().create(BrandService.class);
        }

     public UserService getUserService() throws MalformedURLException{
        return getRetrofit().create(UserService.class);
     }

    // public ProductService getProductService() throws MalformedURLException{
    //     return getRetrofit().create(ProductService.class);
    // }

    public Response<MessageJSON> createUser(User user, UserService service) throws IOException{
        return service
                .createUser(user.getName(), user.getEmail(), user.getPassword(),
                        user.getTitle(), user.getBirthDay(), user.getBirthMonth(),
                        user.getBirthYear(), user.getFirstName(), user.getLastName(),
                        user.getCompany(), user.getAddress1(), user.getAddress2(),
                        user.getCountry(), user.getZipcode(), user.getState(), user.getCity(),
                        user.getMobile_number()
                        )
                .execute();
    }

    public Response<Products> postProductSearch(String product, ProductService service) throws IOException {
        return service
                .searchProduct(product)
                .execute();
    }

    public Response<MessageJSON> postProductSearchNoParameter(ProductService service) throws IOException {
            return service
                    .searchProduct()
                    .execute();
    }

    public Response<Products> getProductList(ProductService service) throws IOException {
        return service
                .getProductList()
                .execute();
    }

    public Response<MessageJSON> postProductList(ProductService service) throws IOException {
        return service
                .postProductList()
                .execute();
    }

    public Response<Brands> getBrandList(BrandService service) throws IOException {
        return service
                .getBrandList()
                .execute();
    }

    public Response<MessageJSON> putBrandList(BrandService service) throws IOException {
        return service
                .putBrandList()
                .execute();
    }
    // public Response<Products> createProductResponse(Product product, ProductService service) throws IOException {
    //     return service
    //             .createProduct(product)//(int)productId) long productId
    //             .execute();
    // }

    // public Response<Product> updateProductResponse(Product product, ProductService service) throws IOException {
    //     return service
    //             .updateProduct(product)//(int)productId) long productId
    //             .execute();
    // }

    // public Response<ResponseBody> deleteProductResponse(Long productId, ProductService service) throws IOException {
    //     return service
    //             .deleteProduct(productId)
    //             .execute();
    // }

    // public Response<Category> getCategoryResponse(Integer categoryId, CategoryService service) throws IOException {
    //   return service
    //             .getCategory(categoryId)
    //             .execute();
    // }

    // public Response<Category> updateCategoryResponse(Integer categoryId, CategoryService service) throws IOException {
    //     return service
    //             .updateCategory(categoryId)
    //             .execute();
    // }

    // public Response<Category> deleteCategoryResponse(Integer categoryId, CategoryService service) throws IOException {
    //     return service
    //             .deleteCategory(categoryId)
    //             .execute();
    // }

    // public Response<Category> createCategoryResponse(Category category, CategoryService service) throws IOException{
    //     return service
    //             .createCategory(category)
    //             .execute();
    // }

    // public static Response<ResponseBody> getAllProductsResponse(ProductService service) throws IOException {
    //     return service
    //             .getAllProducts()
    //             .execute();
    // }
}
