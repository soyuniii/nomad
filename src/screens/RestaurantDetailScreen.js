import React, { useEffect, useState, useRef } from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity, FlatList, Alert } from 'react-native';
import { getRestaurantById } from '../services/api';
import { useReview } from '../contexts/ReviewContext';
import ReviewModal from '../components/ReviewModal';


const RestaurantDetail = ({route}) => {
  const { restaurantId } = route.params;
  const { restaurantReviews, addReview } = useReview();
  const [restaurant, setRestaurant] = useState(null);
  const [modalVisible, setModalVisible] = useState(false);
  const { fetchRestaurantReviews, getRestaurantReviewCount } = useReview();

  const flatListRef = useRef(null);
  const scrollToReviews = () => {
    if (flatListRef.current && flatListRef.current.props.data && flatListRef.current.props.data.length > 0) {
      flatListRef.current.scrollToIndex({ index: 0, animated: true });
    }
  };
  // useEffect(() => {
  //   if ( restaurantReviews[restaurantId]?.length > 0) { 
  //     scrollToReviews(); // 리뷰가 업데이트된 후 스크롤 (restaurantReviews업데이트 이후 가져오기 위해 reviewCount를 바로 사용하지 않음)
  //   }
  // }, [restaurantReviews[restaurantId]?.length]); // 레스토랑 리뷰수 바뀔때 마다 스크롤 변경

  useEffect(() => {
    const fetchRestaurantDetails = async() => {
      try {
      const responseRestaurant = await getRestaurantById(restaurantId);
      setRestaurant(responseRestaurant.data);
      await fetchRestaurantReviews(restaurantId); //전역상태 레스토랑 리뷰 가져오기
      } catch (error) {
        console.error('식당 정보를 가져오는 데 실패했습니다.', error);
      }
    };
    fetchRestaurantDetails();
  }, [restaurantId]); //아이디가 변경될때 서버와 동기화


  if (!restaurant) {
    return (
      <View style={styles.center}>
        <Text>식당 정보를 가져오고 있습니다..</Text>
      </View>
    );
  }

  const reviews = restaurantReviews[restaurantId] || [];
  const reviewCount = getRestaurantReviewCount(restaurantId);

  return (

    <View style={{ flex: 1, backgroundColor: '#fff'}}>
    {/* 고정 헤더 */}
      <View style={styles.fixedHeader}>
        <Text style={styles.name}>{restaurant.name}</Text>
        <Text style={styles.category}>{restaurant.category}</Text>
      </View>

    <FlatList
      ref={flatListRef}
      data={reviews}
      initialNumToRender={5}   // 처음에 5개만 렌더링
      maxToRenderPerBatch={5}  // 한 번에 5개씩 추가 렌더링
      keyExtractor={(item, index) => index.toString()}
      ListHeaderComponent={
        <View style={styles.container}>
          <Text style={styles.address}>📍 {restaurant.address}</Text>
          <Text style={styles.weekdays}>📍 평일: {restaurant.weekdays}</Text>
          <Text style={styles.weekend}>📍 주말: {restaurant.weekend}</Text>
          <Text style={styles.rating}>📍 평점: {restaurant.averageRating}</Text>
          <Text style={styles.reviewCount}>📍 리뷰 수: {reviewCount}</Text>
          <Image source={{ uri: restaurant.imageUrl }} style={styles.restaurantImage} resizeMode='cover'/>

          {/* 리뷰 보기 버튼 */}
          <View style={styles.reviewButton}>
          <TouchableOpacity style={styles.showReview}   activeOpacity={0.7} onPress={() => setModalVisible(true)}>
            <Text style={styles.showReviewText}>리뷰작성</Text>
          </TouchableOpacity>

          <ReviewModal
            visible={modalVisible}
            onClose={() => setModalVisible(false)}
            onSubmit={async (reviewDTO) => {
              try {
                await addReview(restaurantId, reviewDTO);
                Alert.alert("리뷰 등록 완료!");
              } catch (error) {
                Alert.alert("리뷰 등록 실패", error.message);
              }
              setModalVisible(false);
              scrollToReviews();
            }}
            review={null}
            mode="create"
          />

          <TouchableOpacity style={styles.showReview} activeOpacity={0.7} onPress={scrollToReviews}>
            <Text style={styles.showReviewText}>리뷰보기</Text>
          </TouchableOpacity>
          </View>

        </View>
      }
      renderItem={({ item }) => (
        <View style={styles.reviewItem}>
          <View style={styles.reviewHeader}>
            <Text style={styles.reviewNickname}>{item.nickName} ({item.nationality})</Text>
            <Text style={styles.reviewRating}>★{item.rating}</Text>
          </View>
          <Text>{item.message}</Text>
          {item.imageURL && (
            <Image source={{ uri: item.imageURL }} style={styles.reviewImage} />
          )}
          <Text>{item.createdAt} 작성됨</Text>
        </View>
      )}
      style={{ marginTop: 100 }}
    />

    </View>
  );
};

const styles = StyleSheet.create({
    fixedHeader: {
      position: 'absolute',
      top: 0,
      left: 0,
      right: 0,
      backgroundColor: '#fff',
      padding: 27,
    },
    name: {
      fontSize: 26,
      fontWeight: 'bold',
      marginBottom: 10,
    },
    category: {
      fontSize: 18,
      color: '#888',
    },
    container: {
      padding: 20,
      backgroundColor: '#fff',
    },
    restaurantImage: {
      width: '100%',
      height: 200,
      borderRadius: 8,
      marginTop: 25,
    },
    address: {
      fontSize: 16,
      marginBottom: 5,
    },
    weekdays: {
      fontSize: 16,
      marginBottom: 5,
    },
    weekend: {
      fontSize: 16,
      marginBottom: 3,
    },
    rating: {
      fontSize: 16,
      marginBottom: 3,
    },
    reviewCount: {
      fontSize: 16,
      marginBottom: 5,
    },
    center: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
    },
    reviewButton: {
      flex: 1,
      flexDirection: 'row',
      justifyContent: 'center',
      alignItems: 'center',
      gap: 60,
      padding: 10,
      marginTop: 50,
    },
    showReview: {
      backgroundColor: '#f9f9f9',
      padding: 10,
      paddingHorizontal: 40,
      borderRadius: 8,
      shadowColor: '#000',
      shadowOffset: { width: 0, height: 1 },
      shadowOpacity: 0.2,
      shadowRadius: 2,
    },
    showReviewText: {
      fontSize: 17,
      fontWeight: 'bold',
    },
    reviewItem: {
      backgroundColor: '#f9f9f9',
      padding: 15,
      borderRadius: 8,
      marginBottom: 10,
      gap: 3,
      shadowColor: '#000',
      shadowOffset: { width: 0, height: 1 },
      shadowOpacity: 0.2,
      shadowRadius: 2,
    },
    reviewHeader: {
      flexDirection: 'row',
      justifyContent:'space-between',
      alignItems: 'center',
    },
    reviewNickname:{
        fontWeight: 'bold',
        fontSize: 15,
    },
    reviewRating:{
        fontWeight: 'bold',
        fontSize: 15,
    },
    reviewImage: {
      width: '100%',
      height: 200,
      marginTop: 10,
      borderRadius: 5,
    },
  });

export default RestaurantDetail;