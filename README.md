# ⚔️ DUELO DE CARTAS - Sistema Completo

Un juego estratégico de cartas desarrollado en Java puro siguiendo todas las especificaciones del proyecto.

## 📋 Características Implementadas

### RF1 - Sistema de Cartas ✅
- **Cartas de Monstruo**: Cada una con ATK, DEF, Nivel (1-12) y nombre
- **Cartas Mágicas**: Con efectos diversos que se activan inmediatamente
- **Mazo**: 40 cartas totales distribuidas (30 monstruos + 10 mágicas)
- **Distribución**: Cada jugador recibe 20 cartas aleatorias al inicio

### RF2 - Mecánica de Turnos ✅
- **Inicio**: Cada jugador comienza con 5 cartas
- **Turno Aleatorio**: Se determina aleatoriamente quién comienza
- **Fase de Robo**: Cada turno roba 1 carta al inicio
- **Jugar Cartas**: Puede jugar 1 sola carta por turno (monstruo o magia)
- **Restricción de Ataque Inicial**: Monstruos no pueden atacar el primer turno del duelo
- **Derrota por Mazo Vacío**: Si no hay cartas para robar, el jugador pierde

### RF3 - Sistema de Combate ✅
- **Combate Monstruo vs Monstruo**:
  - Si ATK atacante > DEF defensor → Se destruye el defensor
  - Diferencia de daño se descuenta de LP si estaba en ataque
- **Ataque Directo**: Si no hay monstruos defensores → Daño igual al ATK
- **Puntos de Vida**: Cada jugador inicia con 8000 LP
- **Victoria**: Cuando un jugador llega a 0 LP o se queda sin cartas

### RF4 - Cartas Mágicas Complejas ✅
Implementadas 10 cartas mágicas con efectos variados:
1. **Cáliz de Avaricia**: Roba 2 cartas adicionales
2. **Recuperación Vital**: Recupera 2000 LP
3. **Aniquilación**: Destruye un monstruo rival
4. **Terraformación**: Aumenta ATK +1000 por 2 turnos
5. **Barrera Protectora**: Aumenta DEF +500 permanentemente
6. **Robo Rápido**: Roba 1 carta
7. **Sanación**: Recupera 1000 LP
8. **Golpe del Rayo**: Inflige 1000 daño al rival
9. **Potenciador de Ataque**: Aumenta ATK +500 por 1 turno
10. **Maldición de Daño**: Inflige 500 daño al rival

### RF5 - Interfaz de Consola ✅
- **Menú de Inicio**: Con nombres de duelistas
- **Estado del Campo**:
  - Muestra monstruos en el campo de ambos jugadores
  - Puntos de vida (LP) de ambos
  - Cartas en mano
- **Menús Numerados**: Todas las acciones se seleccionan mediante menús
- **Mensaje Final**: Al terminar, anuncia al ganador con estilo temático

## 🎮 Cómo Jugar

1. **Iniciar el juego**:
   ```
   javac -d bin src\*.java
   java -cp bin App
   ```

2. **Ingresa los nombres de los duelistas** cuando se te pida

3. **En cada turno**:
   - **Fase de Robo**: Automática (roba 1 carta)
   - **Fase Principal**: Elige jugar una carta o pasar
     - Si es monstruo: Se invoca en el campo
     - Si es magia: Se activa inmediatamente
   - **Fase de Ataque**: Selecciona monstruos para atacar
     - Atacar monstruo rival o ataque directo

4. **Victoria**: Reduce el LP del rival a 0 o hazte con su mazo

## 📂 Estructura del Proyecto

```
mini_proyecto1/
├── src/
│   ├── App.java                 # Punto de entrada principal
│   ├── Carta.java               # Clase base abstracta para cartas
│   ├── EfectoCarta.java         # Enumeración de efectos mágicos
│   ├── CartaMonstruo.java       # Cartas de monstruo
│   ├── CartaMagica.java         # Cartas mágicas
│   ├── Mazo.java                # Mazo de 40 cartas
│   ├── Jugador.java             # Representa un jugador
│   ├── SistemaCombate.java      # Lógica de combate
│   └── Duelo.java               # Control principal del duelo
├── bin/                         # Bytecode compilado
└── README.md                    # Este archivo
```

## 🛠️ Tecnologías Utilizadas

- **Java SE**: Solo librerías estándar (java.util, java.io)
- **Paradigma**: Programación Orientada a Objetos (POO)
- **Patrón**: Modelo de Juego por Turnos

## 📊 Reglas Específicas Implementadas

### Cartas de Monstruo
- Nivel: 1-12
- ATK/DEF: Puntos de ataque y defensa variables
- Pueden invocar múltiples monstruos en campo
- Solo pueden atacar una vez por turno (excepto efectos especiales)

### Efectos de Cartas Mágicas
- **De Robo**: Permiten robar 1 o 2 cartas del mazo
- **De Recuperación**: Restauran 1000 o 2000 LP
- **De Aumento**: Incrementan ATK/DEF temporalmente
- **De Destrucción**: Eliminan monstruos rivales
- **De Daño Directo**: Reducen LP del rival sin combate

### Sistema de Turnos
```
TURNO:
1. FASE DE ROBO: Jugador roba 1 carta
2. FASE PRINCIPAL: Juega 1 carta (monstruo o magia)
3. FASE DE ATAQUE: Ataca con monstruos en campo
   - Monstruo vs Monstruo
   - Ataque Directo (si no hay defensores)
```

## 🎯 Características Extras

- 🎲 **Sistema de probabilidad real**: Quién comienza es aleatorio
- 📱 **Interfaz amigable**: Emojis y formato visual mejorado
- 🔄 **Efectos temporales**: Modificadores de ATK con duración
- 💾 **Sin dependencias externas**: Solo Java estándar
- 🌐 **Completamente en español**: Nombres, descripciones y interfaz

## ✨ Mejoras Implementadas

Más allá de los requisitos básicos:
- Cartas mágicas con efectos variados y complejos
- Sistema de bonificadores temporales de ATK
- Interfaz de consola mejorada con emojis y formateo
- Validación completa de entrada de usuario
- Mensajes descriptivos en cada acción
- Gestión correcta de excepciones

---

**Desarrollado siguiendo:**
- RF1: Sistema de Cartas ✅
- RF2: Mecánica de Turnos ✅
- RF3: Sistema de Combate ✅
- RF4: Cartas Mágicas Complejas ✅
- RF5: Interfaz de Consola Completa ✅
