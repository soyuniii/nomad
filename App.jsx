<<<<<<< HEAD
import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import SplashScreenComponent from './SplashScreenComponent';  // 스플래시 화면 import
import HomeScreen from './HomeScreen';  // 회원가입 화면 import
import SignUpScreen from './SignUpScreen';  // 회원가입 화면
import SignInScreen from './SignInScreen';  // 로그인 화면
import Experiment1 from './experiments/Experiments1';  // 실험실 1 import
import Experiment2 from './experiments/Experiments2';  // 실험실 2 import
import Experiment3 from './experiments/Experiments3';  // 실험실 3 import


const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        {/* 스플래시 화면 */}
        <Stack.Screen 
          name="Splash" 
          component={SplashScreenComponent} 
          options={{ headerShown: false }}  // 스플래시 화면에서는 헤더를 숨깁니다
        />
        
        {/* 회원가입 화면 */}
        <Stack.Screen 
          name="Home" 
          component={HomeScreen} 
          options={{ headerShown: false }}  // 회원가입 화면에서 헤더 숨기기
        />
        
        {/*회원가입 화면으로 이동 */}
        <Stack.Screen 
          name="SignUp" 
          component={SignUpScreen} 
          options={{ title: '회원가입' }} 
        />

        {/*로그인 화면으로 이동 */}
        <Stack.Screen 
          name="SignIn" 
          component={SignInScreen} 
          options={{ title: '로그인' }} 
        />
        
        {/* 실험실 화면들 */}
        <Stack.Screen name="Experiment1" component={Experiment1} />
        <Stack.Screen name="Experiment2" component={Experiment2} />
        <Stack.Screen name="Experiment3" component={Experiment3} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
=======
import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import SplashScreenComponent from './SplashScreenComponent';  // 스플래시 화면 import
import HomeScreen from './HomeScreen';  // 회원가입 화면 import
import SignUpScreen from './SignUpScreen';  // 회원가입 화면
import LoginScreen from './LoginScreen';  // 로그인 화면
import IntroScreen from './IntroScreen'; // 인트로 화면
import Experiment1 from './experiments/Experiments1';  // 실험실 1 import
import Experiment3 from './experiments/Experiments3';  // 실험실 3 import

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        {/* 스플래시 화면 */}
        <Stack.Screen 
          name="Splash" 
          component={SplashScreenComponent} 
          options={{ headerShown: false }}  // 스플래시 화면에서는 헤더를 숨깁니다
        />
        
        {/* 홈 화면 */}
        <Stack.Screen 
          name="Home" 
          component={HomeScreen} 
          options={{ headerShown: false }}  //화면에서 헤더 숨기기
        />
        
        {/*회원가입 화면 */}
        <Stack.Screen 
          name="SignUp" 
          component={SignUpScreen} 
          options={{ title: '회원가입' }} 
        />
        
        {/* 로그인 화면 */}
        <Stack.Screen
          name="Login"
          component={LoginScreen}
          options={{ title: '로그인' }}
        />
        
        {/* 인트로 화면 */}
        <Stack.Screen name="Intro" component={IntroScreen} />
        
        {/* 실험실 화면들 */}
        <Stack.Screen name="Experiment1" component={Experiment1} />
        <Stack.Screen name="Experiment3" component={Experiment3} />

      </Stack.Navigator>
    </NavigationContainer>
  );
}
>>>>>>> d46ab08a112f3603a4125ff35f788136c9f41c92
