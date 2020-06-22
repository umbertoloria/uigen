# RAD: project_name

Nome widget | Descrizione
------------|------------
label       | contiene testo
text        | input testuale (singola riga)
select      | scelta singola
flag        | campo booleano
box         |
grid        | 
button      | Pulsante (disabilitato o no)
scrollbox   | dimensioni specificate (o adattate), scrollable (forse all'occorrenza) (x, y, both)


Nome box | Descrizione
---------|------------
lbox     | sequenza di elementi (orizzontali o verticali)


## Contraints
* sfondo default bianco

## Tag grafici
* lbox (flow, gap)
* btn

## Tag logici
* events
* event
* type
* action

## Attributi globali
* id

## Eventi
* default
* mousedown
* mouseup

## Stati
* idle
* hover
* focus


<uigen>
	<logics>
		x
	</logics>
	<ui>
		<lbox flow="vertical" gap="2px">
			<label id="">testo fisso</label>
			<text>testo iniziale</text>
			<btn>
				ciao
				<events>
					<event>
						<type>default</type>
						<action>funzione</action>
					</event>
					<event>
						<type>mouseup</type>
						<action>funzione</action>
					</event>
				</events>
			</btn>
		</lbox>
	</ui>
</uigen>



+--------------------------------+
|testo fisso                     |
|\testo iniziale\                |
|:ciao:                          |
+--------------------------------+




# SDD: project_name

element: componenti della ui
box: element che contiene altri element
