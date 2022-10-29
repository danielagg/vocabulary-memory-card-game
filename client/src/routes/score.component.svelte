<script lang="ts">
    import { score } from './store'

    let scoreValue;
	score.subscribe(x => scoreValue = x);

</script>

<div class="lg:self-end flex flex-col items-center lg:items-end mt-8 lg:mt-0">
    <h2 class="text-5xl font-bold">{scoreValue}</h2>
    <p class="text-sm uppercase">Score</p>
</div>