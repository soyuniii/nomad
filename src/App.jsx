import 'react-native-gesture-handler';
import React from 'react';
import { View, Text } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';
import { UserProvider } from './contexts/UserContext';

import SignUpScreen from './screens/SignUpScreen';
import SignInScreen from './screens/SignInScreen';
import HomeScreen from './screens/HomeScreen';
import RestaurantDetailScreen from './screens/RestaurantDetailScreen';
import ProfileScreen from './screens/ProfileScreen';
import MyReviewScreen from './screens/MyReviewScreen';


const Stack = createStackNavigator();

const App = () => {
  return (
    <UserProvider>
      <NavigationContainer>
      <Stack.Navigator initialRouteName="SignIn">

      <Stack.Screen 
      name='SignUp' 
      component={SignUpScreen} 
      options={{
          headerShown: true,
          title: '',
          headerBackTitle: '로그인',
        }
      
      }/>
      <Stack.Screen name='SignIn' component={SignInScreen} options={{headerShown: false}}/>

      <Stack.Screen
        name='Home'
        component={HomeScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen
        name="RestaurantDetail"
        component={RestaurantDetailScreen}
        options={{
          title: '음식점 상세 정보',
          headerBackTitle: '',
          presentation: 'modal'
          }}/>

      <Stack.Screen
        name='Profile'
        component={ProfileScreen}
        options={{
          title: '프로필',
          headerBackTitle: '',
        }}
      />
      <Stack.Screen
        name='MyReview'
        component={MyReviewScreen}
        options={{
          title: '내 리뷰',
          headerBackTitle: '',
        }}
      />

      </Stack.Navigator>
    </NavigationContainer>
    </UserProvider>
  );
};



export default App;