import React,{ useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Image } from 'react-native';

const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = () => {
    // 임시로 이메일과 비밀번호 확인하는 로직
    if (email !== 'test@example.com' || password !== 'password') {
      setErrorMessage('아이디 / 패스워드가 일치하지 않습니다');
    } else {
      setErrorMessage('');
      // 로그인 성공 처리
    }
  };

  return (
    <View style={styles.container}>
      <Image
        source={require('./assets/images/logo.png')}
        style={styles.logoImage}
      />
      <Text style={styles.title}>nom:ad</Text>
      <Text style={styles.subtitle}>고향의 맛을 찾아 떠도는 {`\n`}당신을 위한 가이드</Text>
      
      <TextInput
        style={styles.input}
        placeholder="이메일"
        value={email}
        onChangeText={setEmail}
        autoCapitalize="none"
        keyboardType="email-address"
      />
      <TextInput
        style={styles.input}
        placeholder="패스워드"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      
      {errorMessage ? <Text style={styles.errorText}>{errorMessage}</Text> : null}
      
      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>로그인</Text>
      </TouchableOpacity>
      
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
    padding: 30,
  },
  title: {
    fontSize: 45,
    color: '#000000',
    letterSpacing: 3,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 16,
    color: '#000000',
    marginBottom: 40,
    textAlign: 'center',
  },
  input: {
    width: '100%',
    height: 50,
    backgroundColor: '#F0F0F0',
    borderRadius: 8,
    paddingHorizontal: 15,
    marginBottom: 20,
  },
  errorText: {
    color: 'red',
    marginBottom: 20,
  },
  button: {
    width: '100%',
    height: 50,
    backgroundColor: '#D4B08C',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 8,
  },
  buttonText: {
    color: '#000000',
    fontSize: 16,
    fontWeight: 'bold',
  }
  
});

export default LoginScreen;