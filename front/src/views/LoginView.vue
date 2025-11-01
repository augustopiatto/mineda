<template>
  <div class="about">
    <h1 id="hello">Usuários</h1>
    <p>
      <label for="nome" id="labelNome">Nome: </label>
      <input id="nome" type="text" v-model="nome" />
    </p>
    <p>
      <label for="senha" id="labelSenha">Senha: </label>
      <input id="senha" type="password" v-model="senha" />
    </p>
    <button @click="store.login(nome, senha)">Login</button>
    <p v-if="store.erro">{{ store.erro }}</p>
    <p v-if="store.token">{{ store.token }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { usuarioStore } from '@/stores/usuario'

const store = usuarioStore()

const nome = ref<string>('Aluno')
const senha = ref<string>('123456')
</script>
