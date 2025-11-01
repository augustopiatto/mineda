<template>
  <div>
    <p>{{ nome }}</p>
    <p>
      <label id="labelusuario" for="usuario">Nome: </label>
      <input id="usuario" v-model="nome" />
      <input id="usuario2" :value="nome" />
    </p>
    <p>
      <label id="labelsenha" for="senha">Senha: </label>
      <input id="senha" type="password" v-model="senha" />
    </p>
    <p v-if="senha.length < 5">Senha muito curta!</p>
    <p v-else>Senha ok!</p>
    <button @click="inserirUsuario">Cadastrar</button>
    <button @click="atualizar">Atualizar</button>
    <p v-if="erro">{{ erro }}</p>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Nome</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="usuario in usuarios" :key="usuario.id">
          <th>{{ usuario.id }}</th>
          <th>{{ usuario.nome }}</th>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
import { onMounted } from 'vue'

interface usuario {
  id?: number
  nome: string
  senha: string
}

const nome = ref<string>('Teste')
const senha = ref<string>('123')
const erro = ref<string>()

const usuarios = ref<usuario[]>([
  {
    id: 1,
    nome: 'admin',
    senha: 'admin',
  },
  {
    id: 2,
    nome: 'teste',
    senha: '123',
  },
])

async function inserirUsuario() {
  try {
    await axios.post('usuario', {
      nome: nome.value,
      senha: senha.value,
    })
    atualizar()
    nome.value = ''
    senha.value = ''
    erro.value = undefined
  } catch (e) {
    erro.value = (e as Error).message
  }
}

async function atualizar() {
  usuarios.value = (await axios.get('usuario')).data
}

onMounted(() => {
  atualizar()
})
</script>
